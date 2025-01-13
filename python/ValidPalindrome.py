class Solution:
    def isPalindrome(self, s: str) -> bool:
        l = 0
        r = len(s) - 1
        while l < r:
            print("l", l)
            print("r", r)

            while l < r and (ord(s[l].lower()) < 48 or ord(s[l].lower()) > 57) and (
                    ord(s[l].lower()) < 97 or ord(s[l].lower()) > 122):
                l += 1
            while l < r and (ord(s[r].lower()) < 48 or ord(s[r].lower()) > 57) and (
                    ord(s[r].lower()) < 97 or ord(s[r].lower()) > 122):
                r -= 1

            print('truth', s[l].lower(), s[r].lower())
            if s[l].lower() != s[r].lower():
                return False

            l += 1
            r -= 1

        return True
