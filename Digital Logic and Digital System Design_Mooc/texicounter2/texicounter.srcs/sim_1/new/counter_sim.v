`timescale 1ps / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/06/13 19:14:46
// Design Name: 
// Module Name: counter_sim
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


module counter_sim(
    
    ); 
    
    reg DrivePulse=0;
    reg clk=0;
    reg CounterEnable=0;
    reg Wait=0;
    reg Drive=0;
    reg SET=0;
    reg [4:1] SetInput=0;
    reg [4:1] SetIndic=0;
    wire [5:1] Number1;
    wire [5:1] Number2;
    wire [5:1] Number3;
    wire [5:1] Number4;
    wire [5:1] Number5;
    wire [5:1] Number6;
    wire [5:1] Number7;
    wire [5:1] Number8;
        
    Counter countersim(
        DrivePulse,
        clk,
        CounterEnable,
        Wait,
        Drive,
        SET,
        SetInput,
        SetIndic,
        Number1,
        Number2,
        Number3,
        Number4,
        Number5,
        Number6,
        Number7,
        Number8
        );
    
    initial
    begin
    CounterEnable = 1;
    #10 Drive =1;
    #100 Drive =0;
    #10 Wait =1;
    #100 Wait = 0;
    #10 CounterEnable = 0;
    
    #5 SetIndic = 0;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 1;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 2;
    #5 SetInput = 1;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 3;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 4;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 5;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 6;
    #5 SetInput = 1;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 7;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 8;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 9;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 10;
    #5 SetInput = 1;
    #1 SET = 1;
        #1 SET = 0;
    #5 SetIndic = 11;
    #5 SetInput = 0;
    #1 SET = 1;
        #1 SET = 0;
        
    CounterEnable = 1;
    #10 Drive =1;
    #100 Drive =0;
    #10 Wait =1;
    #100 Wait = 0;
    #10 CounterEnable = 0;
    
    
    
    end
    
    
    always #1 clk = ~clk;
    always #10 DrivePulse = ~DrivePulse;
    
    
    
endmodule
