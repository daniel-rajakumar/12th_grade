"""
Programmer: Daniel Rajakumar
Date: May 17, 2022
"""
import turtle

wn = turtle.Screen()
wn.setup(400, 400)
wn.bgcolor('green')

robot = turtle.Turtle()
robot.color("purple")
robot.pensize(3)


# task 1
def one():
    def draw_square():
        robot.pendown()
        for _ in range(4):
            robot.forward(20)
            robot.left(90)
        robot.penup()
        robot.forward(40)

    for _ in range(5):
        draw_square()

# task 2
def two():
    def draw_square(size):
        robot.pendown()
        for _ in range(4):
            robot.left(90)
            robot.forward(20 + (size * 20))
        robot.penup()
        robot.right(45)
        robot.forward(15)
        robot.left(45)

    robot.right(90)
    for i in range(0, 5):
        draw_square(i)
    robot.left(90)

# task 3
def three():
    def draw_poly(n, sz):
        for i in range(n):
            turtle.forward(sz)
            turtle.left(45)

    draw_poly(8, 50)

# task 4
def four():
    def draw_star():
        for _ in range(5):
            turtle.forward(100)
            turtle.right(144)

    draw_star()

# task 5
def five():
    for _ in range(5):
        four()
        turtle.penup()
        turtle.forward(350)
        turtle.right(144)
        turtle.pendown()

# uncommenting a line below will execute that task
# one()
# two()
# three()
# four()
# five()

turtle.mainloop()
