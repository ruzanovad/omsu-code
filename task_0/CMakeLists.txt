cmake_minimum_required(VERSION 3.23)
project(task0)

set(CMAKE_CXX_STANDARD 14)

add_executable(task0 main.cpp Box.h Box.cpp BoxC.cpp BoxC.h Container.cpp Container.h InvalidWeightException.h)
