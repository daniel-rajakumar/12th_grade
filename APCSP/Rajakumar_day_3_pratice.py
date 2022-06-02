"""
Programmer: Daniel Rajakumar
Date: May 25, 2022
"""
import random
import turtle

wn = turtle.Screen()
wn.setup(800, 800)

def one():
    colors = ['blue', 'green', 'red', 'yellow', 'pink']

    def draw(x, y, size):
        turtle.pencolor(colors[random.randint(0, len(colors)- 1)])
        turtle.penup()
        turtle.goto(x, y)
        turtle.pendown()
        for i in range(5):
            turtle.forward(size)
            turtle.right(144)

    turtle.speed(1000)
    turtle.pendown()

    for i in range(100):
        ran_x = random.randint(-400, 400)
        ran_y = random.randint(-400, 400)
        draw(ran_x, ran_y, 100)


















def two():
    turtle.speed(1000)

    def draw(x, y, pen_size, circle_size):
        turtle.pencolor(colors[random.randint(0, len(colors) - 1)])
        turtle.pensize(pen_size)
        turtle.penup()
        turtle.goto(x, y)
        turtle.pendown()


        for i in range(6):
            turtle.left(360/6)
            turtle.circle(circle_size)

    colors = ['blue', 'green', 'red', 'yellow', 'pink']

    for i in range(60):
        ran_x = random.randint(-400, 400)
        ran_y = random.randint(-400, 400)
        ran_circle_size = random.randint(1, 100)
        ran_pen_size = random.randint(1, 9)
        draw(ran_x, ran_y, ran_pen_size, ran_circle_size)


# uncommenting will run the task
# one()
# two()

turtle.mainloop()
