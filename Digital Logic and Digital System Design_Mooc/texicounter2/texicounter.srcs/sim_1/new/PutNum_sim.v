`timescale 1ps / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/06/13 20:17:41
// Design Name: 
// Module Name: PutNum_sim
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


module PutNum_sim(

    );
    
    reg [30:1] Sum=0;
    reg [4:1] InitialFee4=0;
    reg [4:1] InitialFee3=0;
        reg [4:1] InitialFee2=0;
        reg [4:1] InitialFee1=0;
        wire [5:1] Number1;
        wire [5:1] Number2;
        wire [5:1] Number3;
        wire [5:1] Number4;
        wire [5:1] Number5;
        wire [5:1] Number6;
        wire [5:1] Number7;
        wire [5:1] Number8;
        
        PutNum PutNumTest(
            Sum,
            InitialFee4,
            InitialFee3,
            InitialFee2,
            InitialFee1,
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
            #10 Sum = 10;
            #10 InitialFee1 = 'b1;
            #10 InitialFee2 = 'b1;
            #10 InitialFee3 = 'b1;
            #10 InitialFee4 = 'b1;
        end
endmodule
