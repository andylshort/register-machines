# register-machines
A Java program used to simulate the running of arbitrary Register Machines. Based on the syntax and teachings of the examples inline with the "Models of Computation" course at Imperial College London.

## background

In 1935/36, Alan Turing (and Alonzo Church independently) gave negative solutions to Hilbert's Entscheidungsproblem of deciding, when fed any arbitrary computer program and data, whether said program will terminate or not (aka. the "Halting Problem").

Turing and Church both precisely defined what an algorithm is, and developed approaches to be able to use algorithms as data to other algorithms.

Register machines are the modern incarnation of their approaches. A register machine is a construct that takes in a list of elementary operations/instructions that act on registers that store *natural numbers*. The only instructions supported are incrementation, decrementation, jumps, conditionals, and equal-to-zero tests.

## information

Each instruction is of the format `index: instruction`, where `index` is the line number/index of the instruction, and `instruction` is either an addition, subtraction, or halting instruction. All instances of numbers are natural numbers (i.e. any positive integer >= 0).

#### Add instruction
Increments the value stored in a particular register.

    N: R -> L

Where:

* *N* is the line number of the instruction
* *R* is the register to increment
* *L* is the line number of the instruction to jump to after the register has been incremented

#### Subtract instruction
Decrements the value stored in a particular register.

    N: R -> Lj, Lk

Where:

* *N* is the line number of the instruction
* *R* is the register to decrement
* *Lj* is the line number of the instruction to jump to if, after the register has been decremented, the register value is > 0
* *Lk* is the line number of the instruction to jump to if, after the register has been decremented, the register value is == 0

#### Halting instruction:

Halts the register machine.

    N: HALT

Where:

* *N* is the line number of the instruction


## usage

1. Clone this repository.
2. Compile.
3. Write your register machine program in a `.txt` file, and copy the absolute path to the file.
4. Run the program through the command line with the absolute path as the first argument.
5. Enter in the initial register configuration. This is a space-separated list of integers.
6. Press `Enter` to execute.


## example
Take the following example of a register machine program that adds to input numbers together:

    0: 1 -> 1, 2
    1: 0 -> 0
    2: 2 -> 3, 4
    3: 0 -> 2
    4: HALT

When executed with an initial register configuration of `[0, x, y]`, the program will halt with final configuration of `[x + y, 0, 0]`.

## roadmap
In the future, I plan to implement the following features into the project:

* Command-line arguments and better configuration
* More tests to make the suite comprehensive and improve coverage
* A couple more example programs
* Build tools

Possibly a universal register machine written in the syntax. Only if I'm super bored, though.


## license
The MIT License (MIT)

Copyright (c) 2015 Andrew Lamzed-Short

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.