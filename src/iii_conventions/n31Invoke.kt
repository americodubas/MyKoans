package iii_conventions

import util.TODO


class Invokable(var number: Int = 0)

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}

operator fun Invokable.invoke(): Invokable {
    number++
    return this
}

fun Invokable.getNumberOfInvocations() = this.number