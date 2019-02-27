package tk.mybatis.simple.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import tk.mybatis.simple.model.Country;

public class CountryTest {
	public static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void init(){
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Country> countryList = sqlSession.selectList("selectAll");
			printCountryList(countryList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}

	private void printCountryList(List<Country> countryList) {
		for(Country c : countryList){
			System.out.printf("%-4d%4s%8s\n",c.getId(),c.getCountryname(),c.getCountrycode());
		}
		
	}

}
