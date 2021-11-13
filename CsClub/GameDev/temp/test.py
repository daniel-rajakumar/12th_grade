import pygame, sys
from pygame.locals import *

def main(): 
    pygame.init()
    fpsClock = pygame.time.Clock()

    disp = pygame.display.set_mode((500, 500))
    disp.fill((255, 255, 255))
    pygame.display.set_caption("Hello World")

    x = 50

    while True:

        pygame.draw.circle(disp, (255, 0, 0), (x, 200), 5)

        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()

        pygame.display.update()
        fpsClock.tick(60)

main()

