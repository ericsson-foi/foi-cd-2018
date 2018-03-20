package hr.ericsson.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import hr.ericsson.application.domain.Application;

@Component
public class ApplicationDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	
	public List<Application> getAll() {		
		List<Application> resultList = (List<Application>) jdbcTemplate.query(READ_ALL_QUERY, new RowMapper<Application>() {
			@Override
			public Application mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
				Application result = new Application();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setEmail(rs.getString("email"));
				result.setPosition(rs.getString("position"));
				result.setCompany(rs.getString("company"));
				
				return result;
			}
		});

		return resultList;
	}
	
	public void delete(final Application application) {		
		jdbcTemplate.update(DELETE_QUERY, new Object[] { application.getId() });
	}
	
	public void update(final Application application) {		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(application);
		namedJdbcTemplate.update(UPDATE_QUERY, parameters);
	}
	
	public Application insert(final Application application) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(application);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(INSERT_QUERY, parameters, keyHolder, new String[]{"id"});
		application.setId(keyHolder.getKey().intValue());
		return application;
	}

	
	final static String INSERT_QUERY =  "INSERT INTO public.applications(name, email, position, company VALUES(:name, :email, :position, :company)";
	final static String UPDATE_QUERY = "UPDATE public.applications set name = :name, email = :email, position = :position,  company= :company WHERE id = :id ";
	final static String DELETE_QUERY = "DELETE from public.applications WHERE id = ?";
	final static String READ_ALL_QUERY = "SELECT id, name, email, position, company FROM public.applications";
}
