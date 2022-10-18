@echo off
set xv_path=C:\\Xilinx\\Vivado\\2017.2\\bin
call %xv_path%/xelab  -wto 5b4b03d5401b46df9c9b778280f9f649 -m64 --debug typical --relax --mt 2 -L secureip --snapshot sim_behav xil_defaultlib.sim -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
