`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/06/14 19:17:27
// Design Name: 
// Module Name: Taxi_Counter
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
module Taxi_Counter(
    input CLK,
    input[7:0] Button,
    input smallButton,
    input S2,
    input S0,
    output [7:0] Select,
    output [7:0] Data1,Data2
    );
    wire[4:0] d1,d2,d3,d4,d5,d6,d7,d8;
    Counter counter(S0,CLK,smallButton,Button[6],Button[7],S2,Button[3:0],Button[7:4],d8,d7,d6,d5,d4,d3,d2,d1);
    Scan_Double scan(CLK,d1,d2,d3,d4,d5,d6,d7,d8,Data1,Data2,Select[7:4],Select[3:0]);
endmodule
