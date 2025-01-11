from collections import defaultdict
from typing import List

class Solution:
    def is_valid_sudoku(self, board: List[List[str]]) -> bool:
        rows = defaultdict(set)
        cols = defaultdict(set)
        square = defaultdict(set)
        for row in range(9):
            for col in range(9):
                if board[row][col] == '.': continue
                if (board[row][col] in rows[row]
                        or board[row][col] in cols[col]
                        or board[row][col] in square[(row // 3, col // 3)]):
                    return False
                rows[row].add(board[row][col])
                cols[col].add(board[row][col])
                square[(row//3, col//3)].add(board[row][col])
        return True

solution = Solution()
print(solution.is_valid_sudoku(
    [["1","2",".",".","3",".",".",".","."],
     ["4",".",".","5",".",".",".",".","."],
     [".","9","1",".",".",".",".",".","3"],
     ["5",".",".",".","6",".",".",".","4"],
     [".",".",".","8",".","3",".",".","5"],
     ["7",".",".",".","2",".",".",".","6"],
     [".",".",".",".",".",".","2",".","."],
     [".",".",".","4","1","9",".",".","8"],
     [".",".",".",".","8",".",".","7","9"]]
))