`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/06/13 20:01:00
// Design Name: 
// Module Name: PutNum
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
module PutNum(
    input [30:1] Sum1,
    input [30:1] Sum2,
    input [30:1] Sum3,
    input [4:1] InitialFee4,
    input [4:1] InitialFee3,
    input [4:1] InitialFee2,
    input [4:1] InitialFee1,
    output [5:1] Number1,
    output [5:1] Number2,
    output [5:1] Number3,
    output [5:1] Number4,
    output [5:1] Number5,
    output [5:1] Number6,
    output [5:1] Number7,
    output [5:1] Number8
    
    );
    wire [30:1] Fee;
    assign Fee = Sum1 - Sum3 + Sum2 + InitialFee4*10000 + InitialFee3*1000 + InitialFee2*100 + InitialFee1*10;
    assign Number8 = (Fee/10000000)%10*2;
    assign Number7 = (Fee/1000000)%10*2;
    assign Number6 = (Fee/100000)%10*2;
    assign Number5 = (Fee/10000)%10*2;
    assign Number4 = (Fee/1000)%10*2;
    assign Number3 = (Fee/100)%10*2+1;
    assign Number2 = (Fee/10)%10*2;
    assign Number1 = (Fee/1)%10*2; 

    
endmodule
