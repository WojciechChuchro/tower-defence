// https://leetcode.com/problems/take-gifts-from-the-richest-pile/
class TakeGiftsFromTheRichestPile {

    public static void main(String[] args) {
        int[] arr = {
            25,64,9,4,100
        };
        TakeGiftsFromTheRichestPile tgftrp = new TakeGiftsFromTheRichestPile();

        System.out.println(tgftrp.pickGifts(arr, 4));
    }

    public long pickGifts(int[] gifts, int k) {
        int maxIdx = 0;
        while (k > 0) {
            for (int i = 0; i < gifts.length; i++) {
                if (gifts[maxIdx] < gifts[i]) {
                    maxIdx = i;
                }
            }

            gifts[maxIdx] = (int) Math.floor(Math.sqrt(gifts[maxIdx]));

            k--;
        }
        
        long sum = 0l;
        for(int g : gifts) {
            sum += g;
        }
        
        return sum;
    }
}
