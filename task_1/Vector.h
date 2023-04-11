#include <ostream>
#include <istream>

class Vector {
public:
    Vector();

    Vector(unsigned);

    Vector(unsigned, int);

    Vector(unsigned, unsigned, int);

    Vector(const Vector &);

    Vector(Vector &&);

    ~Vector();

    size_t length() const;

    void resize(unsigned int);

    int &operator[](unsigned);

    const int &operator[](unsigned) const;

    Vector &operator=(const Vector &);

    Vector &operator=(Vector &&);

    Vector operator+(const Vector &);

    bool operator==(const Vector &) const;

    bool operator!=(const Vector &) const;

    bool operator<(const Vector &) const;

    bool operator>(const Vector &) const;

    bool operator<=(const Vector &) const;

    bool operator>=(const Vector &) const;

    bool empty() const;

    friend std::ostream &operator<<(std::ostream &, const Vector &);

    friend std::istream &operator>>(std::istream &, Vector &);

    void reserve(unsigned int);

    unsigned int capacity() const;

    void pushBack(int x);

    int popBack();

private:
    int *base;
    unsigned int size = 10;
    unsigned int reserved = 0;
};