class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        for c in s:
            if c == '(' or c == '[' or c == '{':
                stack.append(c)
            if c == ')' or c == ']' or c == '}' or c == '}':
                if len(stack) == 0:
                    return False
                el = stack.pop()
                if c == ')' and el != '(':
                    return False
                if c == '}' and el != '{':
                    return False
                if c == ']' and el != '[':
                    return False
        if len(stack) != 0:
            return False
        return True




s = Solution()
print(s.isValid("([}])"))