`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/05/29 16:56:00
// Design Name: 
// Module Name: countersim
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


module countersim;
reg j1,k1,clk,r,s;
wire y1,y2,y3,y4;
parameter DELAY = 2;
counter u5(r,s,j1,k1,clk,y1,y2,y3,y4);
initial
begin
j1=1;k1=1;clk=0;r=0;s=1;
end
always #(DELAY<<0)r=1;
always #(DELAY<<1)clk=~clk;

endmodule
