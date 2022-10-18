@echo off
set xv_path=D:\\vivado\\Vivado\\2017.2\\bin
call %xv_path%/xelab  -wto f4440915828e4934a917f58c36377940 -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L unisims_ver -L unimacro_ver -L secureip --snapshot countersim_behav xil_defaultlib.countersim xil_defaultlib.glbl -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
