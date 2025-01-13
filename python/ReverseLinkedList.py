from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev = None
        curr = head
        while curr:
            save = curr.next
            curr.next = prev
            prev = curr
            curr = save
        return prev

s = Solution()

ln = ListNode(1)
ln.next = ListNode(2)
ln.next.next = ListNode(3)
ln.next.next.next = ListNode(4)
print(s.reverseList(ln))
