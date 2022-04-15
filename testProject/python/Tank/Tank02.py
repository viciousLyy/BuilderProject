"""
 v1.1.0
 新增功能：
    创建游戏窗口
    用到游戏引擎的功能模块
"""
import pygame

_display = pygame.display
COLOR_BLACK = pygame.Color(0, 0, 0, )
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
        _display.set_caption("坦克v1.1.0")
        #让窗口持续更新
        while True:
            #窗口颜色填充
            MainGame.window.fill(COLOR_BLACK)
            #窗口更新
            _display.update()
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
MainGame.startGame(0)