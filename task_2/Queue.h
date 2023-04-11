#include <iostream>

class Queue{
private:
    int* arr;
    size_t len = 0;
    size_t MAX_SIZE = 0;
    size_t head = 0;
    size_t tail = -1;
    class Iterator {
        private: Queue& parent;
        size_t pos;
    public:
        //предотвращает неявное преобразование типов при инициализации
        explicit Iterator(Queue& queue) : parent(queue), pos(-1){}
        inline void start() {pos = parent.head;};
        inline void next() {pos = (pos+1)%(parent.MAX_SIZE+1);};
        inline bool finish() const{return pos == (parent.tail+1)%(parent.MAX_SIZE+1);};
        inline int getValue(){return parent.arr[pos];};
    };
public:
    using iterator = Iterator;
    explicit Queue(size_t);
    void push(const int&);
    void push(int&&);

    int front();
    int pop();

    size_t size();
    void make_empty();
    bool empty() const;
    ~Queue();
};