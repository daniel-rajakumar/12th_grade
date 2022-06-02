"""
Programmer: Daniel Rajakumar
Date: May 23, 2022
"""
import turtle

wn = turtle.Screen()
wn.setup(2000, 2000)

# task 1
def one():
    wn.bgcolor('blue')
    turtle.color('white')
    turtle.pendown()
    turtle.speed(10)
    for j in range(2):
        for i in range(8):
            for k in range(3):
                turtle.forward(100)
                turtle.left(k % 2 * 90 + 45)
            turtle.forward(100)
        turtle.left(45 / 2)
    turtle.pendown()


# task 2
def two():
    wn.bgcolor('lightgreen')
    turtle.color('white')
    turtle.pendown()
    turtle.speed(10)
    count = 0
    for i in range(7):
        for j in range(3):
            turtle.forward(300 - (count * 10))
            turtle.left(180 - 60)
            count += 1
    turtle.forward(300 - (count * 10))
    turtle.penup()


# task 3
def three():
    wn.bgcolor('black')
    turtle.color('white')
    turtle.pendown()
    turtle.speed(100)
    turtle.right(45)
    for i in range(50):
        for j in range(3):
            turtle.forward(200 - (i * 2))
            turtle.right(180 - 60)
        turtle.right(20)
    turtle.penup()


# task 4
def four():
    gap = 400
    colors = ['red', 'purple', 'green', 'yellow', 'orange']
    wn.bgcolor('white')
    turtle.speed(1000)

    def drawRect(size):
        turtle.pendown()
        turtle.setheading(0)
        turtle.heading()
        for k in range(len(colors)):
            turtle.color(colors[k % len(colors)])
            for _ in range(4):
                for __ in range(4):
                    turtle.forward(size)
                    turtle.left(-90)
                turtle.left(-90)
            turtle.left(90 / len(colors))
        turtle.penup()

    # center
    drawRect(100)
    turtle.forward(gap / 2)
    turtle.left(90)
    turtle.forward(gap / 2)

    for i in range(1, 5):
        drawRect(50)
        turtle.right(i * 90)
        turtle.forward(gap)


# task 5
def five():
    wn.bgcolor('black')
    colors = ['blue', 'red']
    turtle.speed(100)
    turtle.setheading(90 * 3)
    turtle.heading()
    count = 0
    for i in range(39):
        for j in range(1, 5):
            count += 1
            if count < 39 * 4:
                turtle.color(colors[j % len(colors)])
                turtle.forward(count * 4)
                turtle.right(91)
    turtle.penup()


# task 6
def six():
    colors = ['green', 'pink', 'white', 'orange', 'orchid4']
    wn.bgcolor('white')
    turtle.speed(100)
    turtle.pendown()
    size = 5
    count = 0
    for i in range(360 // size):
        for j in range(4):
            if j == 0 or j == 3:
                turtle.penup()
            else:
                count += 1
                turtle.pendown()
            turtle.color(colors[count % len(colors)])
            turtle.left(90)
            turtle.forward(200)
        turtle.left(size)



# uncommenting will run the task
# one()
# two()
# three()
# four()
# five()
six()

turtle.mainloop()
