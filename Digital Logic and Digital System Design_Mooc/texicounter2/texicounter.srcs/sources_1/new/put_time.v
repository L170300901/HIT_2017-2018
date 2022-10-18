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
module put_time(
    input [30:1] clock,
    output [5:1] Number1,
    output [5:1] Number2,
    output [5:1] Number3,
    output [5:1] Number4,
    output [5:1] Number5,
    output [5:1] Number6,
    output [5:1] Number7,
    output [5:1] Number8
    );
    
    assign Number8 = 20;
    assign Number7 = 20;
    assign Number6 = 20;
    assign Number5 = 20;
    assign Number4 = ((clock/600)%6)*2;
    assign Number3 = ((clock/60)%10)*2+1;
    assign Number2 = ((clock/10)%6)*2;
    assign Number1 = (clock%10)*2; 

    
endmodule
