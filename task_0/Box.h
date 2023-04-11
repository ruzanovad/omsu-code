struct Box{
    int length;
    int width;
    int height;
    double weight;
    int value;
    Box();
    Box(int length, int width, int height, double weight, int value);

    friend std::ostream& operator<< (std::ostream &out, const Box &box);
    friend std::istream& operator>> (std::istream &in, Box &box);

    friend bool operator==(const Box &lhs, const Box &rhs);
};

int sumPrice(Box *boxes, int size);

bool condition(Box *boxes, int size, int max);

double getMaxWeight(Box *boxes, int size, int maxV);

bool checkNesting(Box *boxes, int size);