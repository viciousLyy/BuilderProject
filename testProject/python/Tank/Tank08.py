"""
 v1.1.6
    新增加敌方坦克
    显示敌方坦克
"""
import pygame,random

_display = pygame.display
COLOR_BLACK = pygame.Color(0, 0, 0)
COLOR_WHITE = pygame.Color(225,225,225)
version = "v1.1.6"
class MainGame():
    # 游戏主窗口
    window = None
    SCREEN_HEIGHT = 700
    SCREEN_WIDTH = 1400
    #创建我方坦克
    TANK_P1 = None
    # 创建敌方坦克
    ENEMY_TANK_list = []
    ENEMY_TANK_COUNTS = 7

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
            MainGame.window.blit(self.getTextSurface("剩余敌方坦克%d辆"%10),(0,0))
            MainGame.TANK_P1.displayTank()
            #循环绘制敌方坦克
            self.blitEnemyTank()

            if  MainGame.TANK_P1 and MainGame.TANK_P1.shift:
                MainGame.TANK_P1.move()
            #窗口更新
            _display.update()
    #创建敌方坦克
    def creatEnemyTank(self):
        top  = 25
        speed = random.randint(1,4)
        for i in range(MainGame.ENEMY_TANK_COUNTS):
            #每次循环随机生成一个left值
            left = random.randint(0,13)
            eTank = enemyTank(left*100,top,speed)
            #将生成的敌方坦克加入数组中
            MainGame.ENEMY_TANK_list.append(eTank)
    #将敌方坦克加入主窗口
    def blitEnemyTank(self):
        for eTank in MainGame.ENEMY_TANK_list:
            eTank.displayTank()

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

class Tank():
    def __init__(self,left,top):
        self.imageas = {
            "U":pygame.image.load("images/myTank_U.jpg"),
            "D":pygame.image.load("images/myTank_D.jpg"),
            "R":pygame.image.load("images/myTank_R.jpg"),
            "L":pygame.image.load("images/myTank_L.jpg"),
        }
        self.direction = "U" #默认方向
        self.imagea = self.imageas[self.direction]
        self.rect = self.imagea.get_rect()#坦克区域
        # 指定坦克位置
        self.rect.left = left
        self.rect.top = top
        #速度
        self.speed = 1
        #坦克移动开关
        self.shift = False
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
        pass

    def displayTank(self):
        #重新设置坦克方向
        self.imagea = self.imageas[self.direction]
        #将坦克画布添加到窗口
        MainGame.window.blit(self.imagea,self.rect)

class myTank(Tank):
    def __init__(self):
        pass


class enemyTank(Tank):
    def __init__(self,left,top,speed):
        self.imageas = {
            "U": pygame.image.load("images/enemyTank_U.jpg"),
            "D": pygame.image.load("images/enemyTank_D.jpg"),
            "R": pygame.image.load("images/enemyTank_R.jpg"),
            "L": pygame.image.load("images/enemyTank_L.jpg"),
        }
        self.direction = self.randDirection()  # 默认方向
        self.imagea = self.imageas[self.direction]
        self.rect = self.imagea.get_rect()  # 坦克区域
        # 指定坦克位置
        self.rect.left = left
        self.rect.top = top
        # 速度
        self.speed = 1
        # 坦克移动开关
        self.shift = False
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
    #def displayEnemyTank(self):
        #super().displayTank()

class bullet():
    def __init__(self):
        pass

    def move(self):
        pass

    def displayBullet(self):
        pass


class explode():
    def __init__(self):
        pass

    def displayExplode(self):
        pass


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