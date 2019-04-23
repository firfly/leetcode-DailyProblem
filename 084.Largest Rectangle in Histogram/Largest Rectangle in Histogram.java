答案思路：

思路理解了半天，表示很难想到，那就直接解释好了：

利用一个栈，“递增”的块则直接压入栈中，否则（停止递增时），则将栈顶出栈，乘以上一个比他矮的下标（也就是它出栈后的栈顶）与当前指针的高度，

这样就能表示当前指针的“前一个比它高的块”的最大面积，与最大值比较之后，需要将指针 i 再-1，用来继续探测当前指针“前一块比它高的”。（从下标0一直判断到length，当下标为length的时候，高度为0）
特殊情况：

出栈后，栈内已经为空的时候，说明从最开始（下标 0），到目前为止（下标 i 的前一个），最低点就是刚刚出栈的那一个。所以此时只需要直接用此高度乘以  i  即可（相当于出栈后的栈顶是 - 1）。

 

示例：【2，1，3，5】

下标　　操作

0　　栈内空，0入栈（注意，入栈的是下标，取出来比较的时候要写成height[stack.peek()]）。此时栈内【0】

1　　1比栈顶下标0所表示高度2小，下标0出栈，栈空（前面比2小的没了），所以 area = 2 * i = 2；此时栈内【空】

　　　继续判断，栈空，1入栈。此时栈内【1】

2　　3比栈顶1所示高度1大，下标2入栈。此时栈内【1，2】

3　　5比栈顶2所示高度3大，下标3入栈。此时栈内【1，2，3】

4　　0比栈顶3所示高度5小，下标3出栈，栈顶为2（前面比高度5矮的下标2的那个），所以 area = 5 * （4-2-1） = 5；此时栈内【1，2】

　　　继续判断，0比栈顶2所示高度3小，下标2出栈，栈顶为1（前面比高度3矮的是的下标1的那个），所以 area = 3 * （4-1-1） = 6；此时栈内【1】

　　　继续判断，0比栈顶1所示高度1小，下标1出栈，栈为空，所以 area = 1 * i = 4；此时栈内【空】

　　　继续判断，栈空，0入栈。此时栈内【0】

综上取最大area =》 6

public int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }