from typing import List


class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        set_nums = set(nums)
        result = 0
        for n in set_nums:
            if n - 1 not in set_nums:
                length = 0
                while n + length in set_nums:
                    length +=1
                result = max(result, length)

        return result


s = Solution()
nums = [2,20,4,10,3,4,5]

s.longestConsecutive(nums)

