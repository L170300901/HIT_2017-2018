`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/05/29 21:38:34
// Design Name: 
// Module Name: sim
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


module sim;
parameter DELAY=2;
reg RD,WR,CS,D0,D1,D2,D3;
wire [3:0]DATA;
initial 
    begin
        CS=0;
        RD=0;
        WR=0;
        {D3,D2,D1,D0}=4'b1;
    end
always #DELAY CS=~CS;
always #(DELAY*4) {D3,D2,D1,D0}={D3,D2,D1,D0}+1;
always
    begin
        RD=0;
        #(DELAY*2)
        RD=1;
        #(DELAY*2);
    end
always
    begin
        WR=1;
        #(DELAY*2)
        WR=0;
        #(DELAY*2);
    end
assign DATA=WR&&CS?{D3,D2,D1,D0}:4'bz;
Register u0(RD,WR,CS,DATA);
endmodule

