public class ProductService {
    public static int countByFilter(Filter filter, Goods goods) {
        int count = 0;
        IFoldable[] products = goods.getProducts();
        for (IFoldable p :
                products) {
            if (filter.apply(p.getName())) count++;
        }
        return count;
    }

    public static int countByFilterDeep(Filter filter, Goods goods) {
        int count = 0;
        IFoldable[] products = goods.getProducts();
        for (IFoldable p :
                products) {
            boolean flag = false;
            IFoldable[] arrayOfFoldable = p.getArray();
            if (arrayOfFoldable.length == 0)
                flag = filter.apply(p.getName());
            else {
                flag = recFilter(filter, arrayOfFoldable, flag);
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }

    private static boolean recFilter(Filter filter, IFoldable[] product, boolean flag) {
        if (flag)
            return flag;
        boolean newFlag = false;
        for (IFoldable p : product) {
            IFoldable[] arrayOfFoldable = p.getArray();
            if (arrayOfFoldable.length == 0)
                newFlag = filter.apply(p.getName());
            else {
                newFlag = recFilter(filter, arrayOfFoldable, newFlag);
            }
            if (newFlag){
                break;
            }
        }
        return newFlag;
    }

    private static boolean recWeighted(IFoldable[] productSet, boolean flag) {
        for (IFoldable p:
                productSet) {
            IFoldable[] arrayOfFoldable = p.getArray();
            if (arrayOfFoldable.length==0){
                flag &= (p instanceof IWeighted);
            }
            else{
                flag&= recWeighted(arrayOfFoldable, flag);
            }
            if (!flag) break;
        }
        return flag;
    }


    public static boolean checkAllWeighted(Goods goods){
        boolean flag = true;
        IFoldable[] products = goods.getProducts();
        for (IFoldable p:
                products) {
            IFoldable[] arrayOfFoldable = p.getArray();
            if (arrayOfFoldable.length==0){
                flag = (p instanceof IWeighted);
            }
            else{
                flag = recWeighted(arrayOfFoldable, flag);
            }
            if (!flag) break;
        }
        return  flag;
    }
}
