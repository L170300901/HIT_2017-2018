`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/05/29 21:37:32
// Design Name: 
// Module Name: Register
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


module Register(RD,WR,CS,DATA);
input RD;
input WR;
input CS;
input [3:0]DATA;
reg D0,D1,D2,D3;
always@(*)
begin
    if(CS&&WR)
    {D3,D2,D1,D0}=DATA;
    else 
    {D3,D2,D1,D0}={D3,D2,D1,D0};
    
end
assign DATA=RD&&CS?{D3,D2,D1,D0}:4'bz;
endmodule

