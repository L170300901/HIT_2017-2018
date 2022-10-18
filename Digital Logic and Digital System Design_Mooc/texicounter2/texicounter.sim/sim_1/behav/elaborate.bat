@echo off
set xv_path=E:\\Vivado\\Xilinx\\Vivado\\2017.2\\bin
call %xv_path%/xelab  -wto 02b63766457d4e5ca7b00402c2d3419b -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L unisims_ver -L unimacro_ver -L secureip --snapshot counter_sim_behav xil_defaultlib.counter_sim xil_defaultlib.glbl -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
