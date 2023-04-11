#include "iostream"

namespace containers
{
    class BoxC{
    private:
        int length;
        int width;
        int height;
        double weight;
        int value;
    public:
        BoxC();
        BoxC(int length, int width, int height, double weight, int value);

        friend std::ostream& operator<< (std::ostream &out, const BoxC &box);
        friend std::istream& operator>> (std::istream &in, BoxC &box);

        int getLength() const;

        void setLength(int length);

        int getWidth() const;

        void setWidth(int width);

        int getHeight() const;

        void setHeight(int height);

        double getWeight() const;

        void setWeight(double weight);

        int getValue() const;

        void setValue(int value);

        friend bool operator==(const BoxC &lhs, const BoxC &rhs);
    };
}

