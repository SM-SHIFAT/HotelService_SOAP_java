Check application pid running on 8080 port.
netstat -aon |find /i "listening" |find   "8080"

then 

get PID number and run following command:
Taskkill /F /IM pid

Example: Taskkill /F /IM 12704