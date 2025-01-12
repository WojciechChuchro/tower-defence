from typing import List

def generate_parenthesis(n: int) -> List[str]:
    result = []
    stack = []

    def backtrack(open_n, close_n):
        if open_n == close_n == n:
            result.append("".join(stack))
            return
        if open_n < n:
            stack.append('(')
            backtrack(open_n + 1, close_n)
            stack.pop()
        if open_n > close_n:
            stack.append(')')
            backtrack(open_n, close_n + 1)
            stack.pop()
    backtrack(0, 0)

    return result

print(generate_parenthesis(3))