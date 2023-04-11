#include <exception>
#include <string>

class InvalidWeightException : public std::exception{
    std::string _msg = "Cannot add new box (";
public:
    InvalidWeightException(int index){
        _msg.append(std::to_string(index));
        _msg.append(") - out of bounds");
    }
    const char* what() const noexcept override {
        return _msg.c_str();
    }
};