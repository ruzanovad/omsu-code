#include "Queue.h"

Queue::Queue(size_t size) {
    this->MAX_SIZE = size;
    arr = new int[MAX_SIZE + 1];
}

void Queue::push(const int & value) {
    if (MAX_SIZE >= len + 1){
        len++;
        if (tail == -1){
            head = tail = 0;
        }
        else {
            tail = (tail + 1) % (MAX_SIZE + 1);
        }
        arr[tail] = value;

    }
    else{
        throw std::length_error("Out of range");
    }
}

void Queue::push(int && value) {
    if (MAX_SIZE >= len + 1){
        len++;
        if (tail == -1){
            head = tail = 0;
        }
        else {
            tail = (tail + 1) % (MAX_SIZE+1);
        }
        arr[tail] = value;
    }
    else{
        throw std::length_error("Out of range");
    }
}

int Queue::front() {
    if (empty()){
        throw std::length_error("Queue is empty");
    }
    return arr[head];

}

Queue::~Queue() {
    make_empty();
    MAX_SIZE = 0;
    delete[] arr;
    arr = nullptr;
}

size_t Queue::size() {
    return len;
}

int Queue::pop() {
    int ret = front();
    head = (head + 1) % (MAX_SIZE+1);
    len--;
    return ret;
}

void Queue::make_empty() {
    len = 0;
    head = 0;
    tail = -1;
}

bool Queue::empty() const {
    return (len == 0);
}
