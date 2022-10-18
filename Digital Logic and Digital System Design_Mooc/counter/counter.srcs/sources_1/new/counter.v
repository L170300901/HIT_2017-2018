`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/05/29 16:54:04
// Design Name: 
// Module Name: counter
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


module counter(
input r,
input s,
input j1,
input k1,
input clk,
output y1,
output y2,
output y3,
output y4
    );
        wire Q1,QN1,J1,K1,R1,S1,CP1;
    wire Q2,QN2,J2,K2,R2,S2,CP2;
    wire Q3,QN3,J3,K3,R3,S3,CP3;
    wire Q4,QN4,J4,K4,R4,S4,CP4;
   JK u1(Q1,QN1,J1,K1,R1,S1,CP1);
   JK u2(Q2,QN2,J2,K2,R2,S2,CP2);
   JK u3(Q3,QN3,J3,K3,R3,S3,CP3);
   JK u4(Q4,QN4,J4,K4,R4,S4,CP4);
   assign R1=r,R2=r,R3=r,R4=r,S1=s,S2=s,S3=s,S4=s;
   assign CP1=clk,CP2=clk,CP3=clk,CP4=clk;
   assign J1=j1,K1=k1;
   assign J2=Q1,K2=Q1,J3=Q1&Q2&QN4,K3=Q1&Q2,J4=Q1&Q2&Q3,K4=Q1&Q2;
   assign y1=Q1,y2=Q2,y3=Q3,y4=Q4;
endmodule
