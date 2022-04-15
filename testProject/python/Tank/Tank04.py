"""
 v1.1.2
 新增功能：
    主窗口关闭按钮关联事件
    方向控制，子弹发射
"""
import pygame

_display = pygame.display
COLOR_BLACK = pygame.Color(0, 0, 0)
COLOR_WHITE = pygame.Color(225,225,225)
version = "v1.1.2"
class MainGame():
    # 游戏主窗口
    window = None
    SCREEN_HEIGHT = 700
    SCREEN_WIDTH = 1400

    def __init__(self):
        pass

    # 开始游戏
    def startGame(self):
        _display.init()#初始化窗口
        #创建窗口加载窗口
        MainGame.window = _display.set_mode([MainGame.SCREEN_WIDTH,MainGame.SCREEN_HEIGHT])
        #设置标题
        _display.set_caption("坦克"+version)
        #让窗口持续更新
        while True:
            #窗口颜色填充
            MainGame.window.fill(COLOR_BLACK)
            #在循环中持续获取事件
            self.getEvent()
            #绘制文字的小画布粘贴到大画布（窗口）
            MainGame.window.blit(self.getTextSurface("剩余敌方坦克%d辆"%10),(0,0))
            #窗口更新
            _display.update()
    def getEvent(self):
        #获取所有事件
        eventList = pygame.event.get()
        #循环事件列表
        for event in eventList:
            if event.type == pygame.QUIT:#退出
                self.endGame()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    print("左")
                elif event.key == pygame.K_RIGHT:
                    print("右")
                elif event.key == pygame.K_UP:
                    print("上")
                elif event.key == pygame.K_DOWN:
                    print("下")
                elif event.key == pygame.K_SPACE:
                    print("发射")
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
    def __init__(self):
        pass

    def move(self):
        pass

    def shot(self):
        pass

    def displayTank(self):
        pass


class myTank(Tank):
    def __init__(self):
        pass


class enemyTank(Tank):
    def __init__(self):
        pass

    pass


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