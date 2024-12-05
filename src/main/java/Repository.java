

import java.util.List;

//course,student,instructor icin data turunden bagimsiz CRUD islemlerini listeleyen bir interface ihtiyac

public interface Repository<S,U> {

    void createTable();

    void save(Student entity);


    List<S> findAll();

    void update(S entity);

    void deleteById(U id);

    S findById(U id);// tek bir ogrenciyi bulma






}
