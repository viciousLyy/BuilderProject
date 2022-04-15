"""
 v1.2.1
    新增功能：
       爆炸效果
"""
import pygame,random,time

_display = pygame.display
COLOR_BLACK = pygame.Color(0, 0, 0)
COLOR_WHITE = pygame.Color(225,225,225)
version = "v1.2.1"
class MainGame():
    # 游戏主窗口
    window = None
    SCREEN_HEIGHT = 700
    SCREEN_WIDTH = 1400
    #创建我方坦克
    TANK_P1 = None
    #我方坦克炮弹数组
    Bullet_list = []
    #敌方坦克炮弹数组
    EnemyBullet_list = []
    # 创建敌方坦克
    ENEMY_TANK_list = []
    ENEMY_TANK_COUNTS = 4
    #爆炸效果数组
    Explode_list = []
    def __init__(self):
        pass

    # 开始游戏
    def startGame(self):
        _display.init()#初始化窗口
        #创建窗口加载窗口
        MainGame.window = _display.set_mode([MainGame.SCREEN_WIDTH,MainGame.SCREEN_HEIGHT])
        #设置标题
        _display.set_caption("坦克"+version)
        #创建我方坦克
        MainGame.TANK_P1 = Tank(self.SCREEN_WIDTH/2,self.SCREEN_HEIGHT-85)
        #创建敌方坦克
        self.creatEnemyTank()
        #让窗口持续更新
        while True:
            #窗口颜色填充
            MainGame.window.fill(COLOR_BLACK)
            #在循环中持续获取事件
            self.getEvent()
            #绘制文字的小画布粘贴到大画布（窗口）
            MainGame.window.blit(self.getTextSurface("剩余敌方坦克%d辆"%len(MainGame.ENEMY_TANK_list)),(0,0))
            MainGame.TANK_P1.displayTank()
            #循环绘制敌方坦克
            self.blitEnemyTank()

            if  MainGame.TANK_P1 and MainGame.TANK_P1.shift:
                MainGame.TANK_P1.move()
            #调用我方炮弹渲染方法
            self.blitBullet()
            #调用敌方炮弹渲染方法
            self.blitEnemyBullet()
            #调用爆炸效果
            self.displayExplode()
            #窗口更新
            _display.update()
            time.sleep(0.01)
    #创建敌方坦克
    def creatEnemyTank(self):
        top  = 25
        for i in range(MainGame.ENEMY_TANK_COUNTS):
            #每次循环随机生成一个left值
            speed = random.randint(1,4)
            left = random.randint(0,13)
            eTank = enemyTank(left*100,top,speed)
            #将生成的敌方坦克加入数组中
            MainGame.ENEMY_TANK_list.append(eTank)
    #将敌方坦克加入主窗口
    def blitEnemyTank(self):
        for eTank in MainGame.ENEMY_TANK_list:
            if eTank.live:
                eTank.displayTank()
                #敌方坦克移动
                eTank.randMove()
                #敌方坦克发射
                eBullet = eTank.shot()
                #如果敌方炮弹不是None,则加入到炮弹数组
                if eBullet:
                    # 将敌方炮弹装入炮弹数组
                    MainGame.EnemyBullet_list.append(eBullet)
            else:
                MainGame.ENEMY_TANK_list.remove(eTank)
    #渲染我方炮弹
    def blitBullet(self):
        for Bullet in MainGame.Bullet_list:
            #判断炮弹是否存在，否则移除出数组
            if Bullet.live:
                 Bullet.displayBullet()
                 Bullet.move()
                 #调用我方炮弹与敌方坦克碰撞测试
                 bullet.hitEnemyTank(Bullet)
            else:
                MainGame.Bullet_list.remove(Bullet)
    #渲染敌方炮弹
    def blitEnemyBullet(self):
        for eBullet in MainGame.EnemyBullet_list:
            #判断炮弹是否存在，否则移除出数组
            if eBullet.live:
                 eBullet.displayBullet()
                 eBullet.move()
            else:
                MainGame.EnemyBullet_list.remove(eBullet)
    #现实爆炸效果
    def displayExplode(self):
        for e in MainGame.Explode_list:
            if e.live:
                e.displayExplode()
            else:
                MainGame.Explode_list.remove(e)
    def getEvent(self):
        #获取所有事件
        eventList = pygame.event.get()
        #循环事件列表
        for event in eventList:
            if event.type == pygame.QUIT:#退出
                self.endGame()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT or event.key == pygame.K_a:
                   print("左")
                   #修改方向
                   MainGame.TANK_P1.direction = "L"
                   #修改位置
                   #MainGame.TANK_P1.move()
                   MainGame.TANK_P1.shift = True
                elif event.key == pygame.K_RIGHT or event.key == pygame.K_d:
                    print("右")
                    # 修改方向
                    MainGame.TANK_P1.direction = "R"
                    # 修改位置
                    #MainGame.TANK_P1.move()
                    MainGame.TANK_P1.shift = True
                elif event.key == pygame.K_UP or event.key == pygame.K_w:
                    print("上")
                    # 修改方向
                    MainGame.TANK_P1.direction = "U"
                    # 修改位置
                    #MainGame.TANK_P1.move()
                    MainGame.TANK_P1.shift = True
                elif event.key == pygame.K_DOWN or event.key == pygame.K_s:
                    print("下")
                    # 修改方向
                    MainGame.TANK_P1.direction = "D"
                    # 修改位置
                    #MainGame.TANK_P1.move()
                    MainGame.TANK_P1.shift = True
                elif event.key == pygame.K_SPACE:
                    print("发射")
                    if len(MainGame.Bullet_list)<2:
                        m = bullet(MainGame.TANK_P1)#我方坦克发射炮弹
                        MainGame.Bullet_list.append(m)
                    else:
                        print("装弹中。。。")
                    print("炮弹数%d"%len(MainGame.Bullet_list))
            if event.type == pygame.KEYUP:
                if event.key == pygame.K_LEFT or event.key == pygame.K_a:
                   print("左停")
                   #修改 移动开关
                   MainGame.TANK_P1.shift = False
                elif event.key == pygame.K_RIGHT or event.key == pygame.K_d:
                    print("右停")
                    # 修改  移动开关
                    MainGame.TANK_P1.shift = False
                elif event.key == pygame.K_UP or event.key == pygame.K_w:
                    print("上停")
                    # 修改 移动开关
                    MainGame.TANK_P1.shift = False
                elif event.key == pygame.K_DOWN or event.key == pygame.K_s:
                    print("下停")
                    # 修改 移动开关
                    MainGame.TANK_P1.shift = False
    def getTextSurface(self,text):
        #初始化字体模块
        pygame.font.init()
        #选择一个合适的字体
        font = pygame.font.SysFont("youyuan",25)
        #使用对应字符完成内容绘制
        textSurface = font.render(text,True,COLOR_WHITE)
        return textSurface
    def endGame(self):
        print("游戏结束")
        # 结束Python解释器
        exit()

class BaseItem(pygame.sprite.Sprite):#sprite 精灵类
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)#调用精灵自身初始化

class Tank(BaseItem):
    def __init__(self,left,top):
        self.images = {
            "U":pygame.image.load("images/myTank_U.jpg"),
            "D":pygame.image.load("images/myTank_D.jpg"),
            "R":pygame.image.load("images/myTank_R.jpg"),
            "L":pygame.image.load("images/myTank_L.jpg"),
        }
        self.direction = "U" #默认方向
        self.image = self.images[self.direction]
        self.rect = self.image.get_rect()#坦克区域
        # 指定坦克位置
        self.rect.left = left
        self.rect.top = top
        #速度
        self.speed = 2
        #坦克移动开关
        self.shift = False
        #坦克是否存活
        self.live = True
    def move(self):
        if self.direction == "L":
            if self.rect.left > 0+4:
                self.rect.left -= self.speed
        elif self.direction == "R":
            if self.rect.left < MainGame.SCREEN_WIDTH-self.rect.height-5:
                self.rect.left += self.speed
        elif self.direction == "U":
            if self.rect.top > 25:
                self.rect.top -= self.speed
        elif self.direction == "D":
            if self.rect.top < MainGame.SCREEN_HEIGHT-self.rect.height-5:
                self.rect.top += self.speed


    def shot(self):
        #返回子弹方法
        return bullet(self)

    def displayTank(self):
        #重新设置坦克方向
        self.image = self.images[self.direction]
        #将坦克画布添加到窗口
        MainGame.window.blit(self.image,self.rect)

class myTank(Tank):
    def __init__(self):
        pass


class enemyTank(Tank):
    def __init__(self,left,top,speed):
        super(enemyTank,self).__init__(left,top)
        self.images = {
            "U": pygame.image.load("images/enemyTank_U.jpg"),
            "D": pygame.image.load("images/enemyTank_D.jpg"),
            "R": pygame.image.load("images/enemyTank_R.jpg"),
            "L": pygame.image.load("images/enemyTank_L.jpg"),
        }
        self.direction = self.randDirection()  # 默认方向
        self.image = self.images[self.direction]
        self.rect = self.image.get_rect()  # 坦克区域
        # 指定坦克位置
        self.rect.left = left
        self.rect.top = top
        # 速度
        self.speed = 1
        # 坦克移动开关
        self.shift = False
        self.steps = 50
    def randDirection(self):
        num = random.randint(1,4)
        if num == 1:
            return "U"
        elif num == 2:
            return "D"
        elif num == 3:
            return "L"
        elif num == 4:
            return "R"
    def randMove(self):
        if self.steps <=0:
            self.direction = self.randDirection()
            self.steps = 50
        else:
            self.move()
            self.steps -= 1
    def shot(self):
        num = random.randint(1,1000)
        if num < 10:
            return bullet(self)
class bullet(BaseItem):
    def __init__(self,tank):
        #图片
        self.images = {
            "U":pygame.image.load("images/Bullet_U.jpg"),
            "D":pygame.image.load("images/Bullet_D.jpg"),
            "R":pygame.image.load("images/Bullet_R.jpg"),
            "L":pygame.image.load("images/Bullet_L.jpg"),
        }
        #炮弹速度
        self.speed = 5
        #方向（取决于坦克）
        self.direction = tank.direction
        #位置
        self.image = self.images[self.direction]
        self.rect = self.image.get_rect()#炮弹区域
        if self.direction == "U":
            self.rect.left = tank.rect.left + tank.rect.width/2 - self.rect.width/2
            self.rect.top = tank.rect.top - self.rect.height
        elif self.direction == "D":
            self.rect.left = tank.rect.left + tank.rect.width/2 - self.rect.width/3
            self.rect.top = tank.rect.top + tank.rect.height
        elif self.direction == "L":
            self.rect.left = tank.rect.left - self.rect.width
            self.rect.top = tank.rect.top + tank.rect.width/2 - self.rect.height/3
        elif self.direction == "R":
            self.rect.left = tank.rect.left + tank.rect.height
            self.rect.top = tank.rect.top + tank.rect.width/2 - self.rect.height/3
        self.live = True
    def move(self):
        if self.direction == "L":
            if self.rect.left > 0:
                self.rect.left -= self.speed
            else:
                #消除炮弹
                self.live = False
        elif self.direction == "R":
            if self.rect.left < MainGame.SCREEN_WIDTH - self.rect.width:
                self.rect.left += self.speed
            else:
                #消除炮弹
                self.live = False
        elif self.direction == "U":
            if self.rect.top >0:
                self.rect.top -= self.speed
            else:
                #消除炮弹
                self.live = False
        elif self.direction == "D":
            if self.rect.top < MainGame.SCREEN_HEIGHT - self.rect.height - 5:
                self.rect.top += self.speed
            else:
                #消除炮弹
                self.live = False

    def displayBullet(self):
        # 将坦克画布添加到窗口
        MainGame.window.blit(self.image, self.rect)
    def hitEnemyTank(self):
        for eTank in MainGame.ENEMY_TANK_list:
            if pygame.sprite.collide_rect(eTank,self):#检测矩形面积碰撞
                #实现爆炸效果
                Explode = explode(eTank)
                #爆炸效果添加到列表
                MainGame.Explode_list.append(Explode)
                self.live = False
                eTank.live = False
class explode():
    def __init__(self,tank):
        self.rect = tank.rect
        self.step = 0
        self.images = [
            pygame.image.load("explore/1.png"),
            pygame.image.load("explore/2.png"),
            pygame.image.load("explore/3.png"),
            pygame.image.load("explore/4.png"),
            pygame.image.load("explore/5.png"),
            pygame.image.load("explore/6.png"),
            pygame.image.load("explore/7.png"),
            pygame.image.load("explore/8.png"),
            pygame.image.load("explore/9.png"),
            pygame.image.load("explore/10.png"),
            pygame.image.load("explore/11.png"),
            pygame.image.load("explore/12.png"),
            pygame.image.load("explore/14.png"),
            pygame.image.load("explore/15.png"),
            pygame.image.load("explore/16.png"),
            pygame.image.load("explore/17.png"),
            pygame.image.load("explore/18.png"),
            pygame.image.load("explore/19.png"),
            pygame.image.load("explore/20.png"),
            pygame.image.load("explore/20.png"),
            pygame.image.load("explore/22.png"),
            pygame.image.load("explore/23.png"),
            pygame.image.load("explore/24.png"),
            pygame.image.load("explore/26.png"),
            pygame.image.load("explore/27.png"),
            pygame.image.load("explore/29.png"),
            pygame.image.load("explore/30.png"),
        ]
        self.image = self.images[self.step]
        self.live = True
    #展示爆炸效果
    def displayExplode(self):
        if self.step < len(self.images):
            MainGame.window.blit(self.image,self.rect)
            self.image = self.images[self.step]
            self.step += 1
        else:
            self.live = False
            self.step = 0

class wall():
    def __init__(self):
        pass

    def displayWall(self):
        pass


class music():
    def __init__(self):
        pass

    def play(self):
        pass
MainGame().startGame()