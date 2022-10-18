`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/05/24 15:56:39
// Design Name: 
// Module Name: test
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


module ha (a,b,s,c);
input a,b;
output s,c;
xor f1(s,a,b);
and f2(c,a,b);
endmodule



module fa (faa,fb,fc,ss,cc );
input faa;
input fb;
input fc;
output ss;
output cc;

wire t0,t1,t2;

ha ha1(
.a(faa),
.b(fb),
.s(t0),
.c(t1)
);
ha ha2(
.a(fc),
.b(t0),
.s(ss),
.c(t2)
);
or f1(cc,t1,t2);
endmodule

module hztha_sim( );
reg a,b,c0;
wire s,c;
hanguo2 test(a,b,c0,s,c);
parameter delay=2, to =1000;
initial
begin
a=0;b=0;c0=0;
#(delay*to)$finish;
end

always #(delay*2) a=~a;
always #(delay*4) b=~b;
always #(delay*8) c0=~c0;

endmodule
