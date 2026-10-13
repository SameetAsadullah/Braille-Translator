cmake_minimum_required(VERSION 3.16)

project(braille_translator LANGUAGES CXX)

add_executable(braille-translator src/main.cpp)
target_compile_features(braille-translator PRIVATE cxx_std_17)
