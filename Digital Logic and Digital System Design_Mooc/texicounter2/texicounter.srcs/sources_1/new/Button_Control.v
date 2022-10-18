`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/06/18 09:47:37
// Design Name: 
// Module Name: Button_Control
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

(* DONT_TOUCH = "true" *)
module Button_Control(
    input CLK,
    input Switch1,
    input Button,
    output reg D_Switch1,
    output reg D_Button
    );
    reg delaySwitch1;
    reg delayButton;
    reg[21:0] CLKCounter1;
    reg[21:0] CLKCounter2;
    reg clk;
    wire s;
    wire b;
    assign s = Switch1 ^ delaySwitch1;
    assign b = Button ^ delayButton;
    initial begin    CLKCounter1 = 0; CLKCounter2 = 0; clk = 0;   end
    always@(posedge CLK)    begin
        CLKCounter1 = CLKCounter1 + 1;
        if(s == 1)  CLKCounter1 = 0;
        delaySwitch1 = Switch1;
        if(CLKCounter1 >= 3000000)    begin
            D_Switch1 = Switch1;
            CLKCounter1 = 0;
        end
    end
    always@(posedge CLK)    begin
            CLKCounter2 = CLKCounter2 + 1;
            if(b == 1)  CLKCounter2 = 0;
            delayButton = Button;
            if(CLKCounter2 >= 3000000)    begin
                D_Button = Button;
                CLKCounter2 = 0;
            end
        end
endmodule
