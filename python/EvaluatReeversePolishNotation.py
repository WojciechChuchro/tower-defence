from typing import List


class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        operators = ['+', '-', '*', '/']
        for token in tokens:
            if token in operators:
                second = stack.pop()
                first = stack.pop()
                if token == "+":
                    stack.append(first + second)
                elif token == "-":
                    stack.append(first - second)
                elif token == "*":
                    stack.append(first * second)
                elif token == "/":
                    stack.append(int(first / second))
            else:
                stack.append(int(token))

        return stack[-1]



s = Solution()
tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
print(s.evalRPN(tokens))