`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// HIT: 
// YBS 
// 
// Create Date: 2018/06/12 19:04:58
// Design Name: TaxiCounter
// Module Name: Counter
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


(* dont_touch = "true" *)
module Counter(
    input DrivePulse,//S0
    input clk,
    input CounterEnable,//T5
    input Wait,//1
    input Drive,//2
    input SET,//S2
    input [4:1] SetInput,//1-4
    input [4:1] SetIndic,//5-8
    output reg [5:1] Number1,        //87654321              
    output reg [5:1] Number2,
    output reg [5:1] Number3,
    output reg [5:1] Number4,
    output reg [5:1] Number5,
    output reg [5:1] Number6,
    output reg [5:1] Number7,
    output reg [5:1] Number8
    );
    
    (* dont_touch = "true" *) reg [30:1] Sum1;            //记录行车费的和
    (* dont_touch = "true" *) reg [30:1] Sum2;            //记录等待费的和
    (* dont_touch = "true" *) reg [30:1] Sum3;
    (* dont_touch = "true" *) reg [4:1] InitialFee1; //起步价，1，2，3，4分别为百位，十位，个位和十分位
    (* dont_touch = "true" *) reg [4:1] InitialFee2;  
    (* dont_touch = "true" *) reg [4:1] InitialFee3;
    (* dont_touch = "true" *) reg [4:1] InitialFee4; 
    (* dont_touch = "true" *) reg [4:1] WaitFee1;
    (* dont_touch = "true" *)  reg [4:1] WaitFee2;
    (* dont_touch = "true" *) reg [4:1] WaitFee3;
    (* dont_touch = "true" *) reg [4:1] WaitFee4;
    (* dont_touch = "true" *) reg [4:1] DriveFee1;
    (* dont_touch = "true" *) reg [4:1] DriveFee2;
    (* dont_touch = "true" *)  reg [4:1] DriveFee3;
    (* dont_touch = "true" *) reg [4:1] DriveFee4;
    
    (* dont_touch = "true" *) reg [30:1] ClkCounter;  //clk分频，用于等车计价
    (* dont_touch = "true" *) reg [30:1] clock;
    
    wire [5:1] PutNumber1; //Sum1+Sum2+起步价，等于总价，87654321
    wire [5:1] PutNumber2;
    wire [5:1] PutNumber3;
    wire [5:1] PutNumber4;
    wire [5:1] PutNumber5;
    wire [5:1] PutNumber6;
    wire [5:1] PutNumber7;
    wire [5:1] PutNumber8;
    
    wire [5:1] PutTime1; //Sum1+Sum2+起步价，等于总价，87654321
    wire [5:1] PutTime2;
    wire [5:1] PutTime3;
    wire [5:1] PutTime4;
    wire [5:1] PutTime5;
    wire [5:1] PutTime6;
    wire [5:1] PutTime7;
    wire [5:1] PutTime8;
       
    wire drivepulse,set;
    
    PutNum PutNumber(  //输入Sum1,Sum2和InitialFee，计算每一位输出
        Sum1,
        Sum2,
        Sum3,
        InitialFee4,
        InitialFee3,
        InitialFee2,
        InitialFee1,
        PutNumber1,
        PutNumber2,
        PutNumber3,
        PutNumber4,
        PutNumber5,
        PutNumber6,
        PutNumber7,
        PutNumber8
    );
    
    put_time PUTTIME(
        clock,
        PutTime1,
        PutTime2,
        PutTime3,
        PutTime4,
        PutTime5,
        PutTime6,
        PutTime7,
        PutTime8
    );
    
    Button_Control countrol(
        clk,
        DrivePulse,
        SET,
        drivepulse,
        set
    );
    
    
    initial //赋初值
    begin
        Sum1 = 0;
        Sum2 = 0;
        Sum3 = 0;
        Number1 = 0;
        Number2 = 0;
        Number3 = 0;
        Number4 = 0;
        Number5 = 0;
        Number6 = 0;
        Number7 = 0;
        Number8 = 0;
        DriveFee4 = 'b0000;
        DriveFee3 = 'b0000;
        DriveFee2 = 'b0100;
        DriveFee1 = 'b0000;
        WaitFee4 = 'b0000; 
        WaitFee3 = 'b0000;
        WaitFee2 = 'b0000;
        WaitFee1 = 'b1000; 
        InitialFee4 = 'b0000;
        InitialFee3 = 'b0000;
        InitialFee2 = 'b1010;
        InitialFee1 = 'b0000;
        ClkCounter = 0;
        clock =0;
    end
    
    always @(posedge set) //设置各项价格
    begin
        if(CounterEnable==0)
        begin
            case(SetIndic)
                4'b0000:begin DriveFee4 <= SetInput; end
                4'b0001:begin DriveFee3 <= SetInput; end
                4'b0010:begin DriveFee2 <= SetInput; end
                4'b0011:begin DriveFee1 <= SetInput; end
                4'b0100:begin WaitFee4 <= SetInput; end
                4'b0101:begin WaitFee3 <= SetInput; end
                4'b0110:begin WaitFee2 <= SetInput; end
                4'b0111:begin WaitFee1 <= SetInput; end
                4'b1000:begin InitialFee4 <= SetInput; end
                4'b1001:begin InitialFee3 <= SetInput; end
                4'b1010:begin InitialFee2 <= SetInput; end
                4'b1011:begin InitialFee1 <= SetInput; end
            endcase
        end
    end
    
    
    always @(posedge drivepulse)  //计算开车价格
    begin
     if (CounterEnable==1 & Drive==1) 
           begin
               Sum1 <= Sum1 + DriveFee4 * 1000 + DriveFee3 * 100 + DriveFee2 * 10 + DriveFee1 * 1;
           end
    end
    
    always @(posedge clk)  //计算等车价格，输出数字
    begin
        ClkCounter <= ClkCounter + 1;
        if (ClkCounter >= 100000000)
        begin
            if(CounterEnable == 1 && Wait ==1)
            begin
                Sum2 <= Sum2 + WaitFee4 * 1000 + WaitFee3 * 100 + WaitFee2 * 10 + WaitFee1 * 1;
                clock <= clock + 1;
            end
            ClkCounter <= 0;
        end
        if(CounterEnable == 0)
        begin
            Sum3 <= Sum1;
            Sum2 <= 0;
            clock <= 0;
            Number1 <= SetInput*2;
            Number2 <= 0;
            Number3 <= 0;
            Number4 <= 0;
            Number5 <= 0;
            Number6 <= 0;
            Number7 <= 0;
            Number8 <= 0;
        end
        else if(Wait == 1)
        begin
            Number1 <= PutTime1;
            Number2 <= PutTime2;
            Number3 <= PutTime3;                                                                                                                      
            Number4 <= PutTime4;
            Number5 <= PutTime5;
            Number6 <= PutTime6;
            Number7 <= PutTime7;
            Number8 <= PutTime8;
        end
        else if(1)
        begin
            Number1 <= PutNumber1;
            Number2 <= PutNumber2;
            Number3 <= PutNumber3;                                                                                                                      
            Number4 <= PutNumber4;
            Number5 <= PutNumber5;
            Number6 <= PutNumber6;
            Number7 <= PutNumber7;
            Number8 <= PutNumber8;
        end
        
    end
    
endmodule
