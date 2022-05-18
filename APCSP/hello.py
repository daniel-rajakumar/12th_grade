import turtle

wn = turtle.Screen()
wn.setup(400, 400)
wn.bgcolor('green')

robot = turtle.Turtle()
robot.color("purple")
robot.pensize(3)


# one
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

def two():
    def draw_square(size):
        robot.pendown()
        for i in range(4):
            robot.left(90)
            robot.forward(20)
        robot.penup()
        robot.forward(20)
        # robot.left(90)

    for i in range(5):
        draw_square(20 * i)


# one()
two()


turtle.mainloop()
