class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        for c in s:
            if c == '(' or c == '[' or c == '{':
                stack.append(c)
            if c == ')' or c == ']' or c == '}' or c == '}':
                if stack:
                    return False
                el = stack.pop()
                if c == ')' and el != '(':
                    return False
                if c == '}' and el != '{':
                    return False
                if c == ']' and el != '[':
                    return False
        if stack:
            return False
        return True

    def is_valid(self, s: str) -> bool:
        stack = []
        close_to_open = {')': '(', ']': '[', '}': '{'}
        for c in s:
            if c in close_to_open:
                if stack and stack[-1] == close_to_open[c]:
                    stack.pop()
                else:
                    return False
            else:
                stack.append(c)

        return True




s = Solution()
print(s.is_valid("()"))