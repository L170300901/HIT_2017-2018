`timescale 1ms / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2018/06/12 18:21:42
// Design Name: 
// Module Name: Scan_Double
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

module Scan_Double(
    input CLK,  //100MHZµÄÊ±ÖÓ
    input [4:0] d1,d2,d3,d4,d5,d6,d7,d8,
    output reg[7:0] Out_Data1,Out_Data2,
    output reg[3:0] Out_Select1,Out_Select2
);
    reg[1:0] cursor;
    reg[21:0] timer;
    wire[7:0] s1,s2,s3,s4,s5,s6,s7,s8;
    Number27 N1(d1,s1);
    Number27 N2(d2,s2);
    Number27 N3(d3,s3);
    Number27 N4(d4,s4);
    Number27 N5(d5,s5);
    Number27 N6(d6,s6);
    Number27 N7(d7,s7);
    Number27 N8(d8,s8);
    initial begin   Out_Select1 = 4'b1000; Out_Select2 = 4'b1000; cursor = 2'b00; timer = 0;  end
    always@(posedge CLK) 
        begin
            timer = timer + 1;
                if(timer >= 250000)  begin
                cursor = cursor + 1;
                timer = 0;
                end
        end
    always@(cursor)
        begin
            case(cursor)
            2'b00:  begin   Out_Select1 <= 4'b1000; Out_Select2 <= 4'b1000; Out_Data1 <= s1; Out_Data2 <= s5;  end
            2'b01:  begin   Out_Select1 <= 4'b0100; Out_Select2 <= 4'b0100; Out_Data1 <= s2; Out_Data2 <= s6;  end
            2'b10:  begin   Out_Select1 <= 4'b0010; Out_Select2 <= 4'b0010; Out_Data1 <= s3; Out_Data2 <= s7;  end
            2'b11:  begin   Out_Select1 <= 4'b0001; Out_Select2 <= 4'b0001; Out_Data1 <= s4; Out_Data2 <= s8;  end
            endcase
        end
endmodule

