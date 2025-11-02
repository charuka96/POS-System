package repository;
import repository.custom.OrderRepository;
import repository.custom.impl.CustomerRepositoryImpl;
import repository.custom.impl.ItemRepositoryImpl;
import repository.custom.impl.OrderRepositoryImpl;
import service.custom.impl.OrderServiceImp;
import util.RepositoryType;


public class RepositoryFactory {
    private static RepositoryFactory instance;
    private RepositoryFactory(){}
    public static RepositoryFactory getInstance() {
        return instance == null?instance =new RepositoryFactory():instance;
    }
    public <T extends SuperRepository>T getRepositoryType(RepositoryType type){
        switch (type){
            case CUSTOMER:return ( T) new CustomerRepositoryImpl();
            case ITEM:return (T) new ItemRepositoryImpl();
            case ORDER:return (T)new OrderRepositoryImpl();
        }
        return null;
    }
}