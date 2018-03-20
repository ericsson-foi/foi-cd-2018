package hr.ericsson.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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

import hr.ericsson.application.domain.SampleType;

@Component
public class TestDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	/*
	 * TODO rowmapper bi trebalo izdvojiti u zasebnu metodu ako će se zvati više
	 * od jednom, a u 90% slucajeva hoce
	 */
	public List<SampleType> getSampleTypes() {		
		List<SampleType> resultList = (List<SampleType>) jdbcTemplate.query(READ_ALL_QUERY, new RowMapper<SampleType>() {
			@Override
			public SampleType mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
				SampleType result = new SampleType();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("sample_type_name"));
				result.setStatus(rs.getInt("status"));
				result.setType(rs.getString("s_type"));
				result.setDate(new Date());
				return result;
			}
		});

		return resultList;
	}
	
	public void deleteSampleType(final SampleType sampleType) {		
		jdbcTemplate.update(DELETE_QUERY, new Object[] { sampleType.getId() });
	}
	
	public void updateSampleType(final SampleType sampleType) {		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(sampleType);
		namedJdbcTemplate.update(UPDATE_QUERY, parameters);
	}
	
	public SampleType insertSampleType(final SampleType sampleType) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(sampleType);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(INSERT_QUERY, parameters, keyHolder, new String[]{"id"});
		sampleType.setId(keyHolder.getKey().intValue());
		return sampleType;
	}

	
	final static String INSERT_QUERY =  "INSERT INTO test.sample_types(sample_type_name, status, s_type) VALUES (:name, 0, :type)";
	final static String UPDATE_QUERY = "UPDATE test.sample_types set sample_type_name = :name, s_type = :type, status = :status WHERE id = :id ";
	final static String DELETE_QUERY = "DELETE from test.sample_types WHERE id = ?";
	final static String READ_ALL_QUERY = "SELECT id, sample_type_name, status, s_type FROM   test.sample_types";
}
