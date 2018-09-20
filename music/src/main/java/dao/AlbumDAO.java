package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import model.*;
import java.util.Collection;
import java.util.ArrayList;

public class AlbumDAO {
    private JdbcTemplate jdbcTemplate;

    public AlbumDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Album createAlbum(Album album){


        this.jdbcTemplate.update("INSERT INTO albums(id, title) VALUES (?,?)", album.getId(), album.getTitle());

        return album;
    }

    public Album getAlbum(int id){
        Album album = new Album(id, "");
        //this.jdbcTemplate.executeQuery("SELECT * FROM albums WHERE id = (?) "), album.getId();
        //Get album and set tracks using getTracksByAlbumId(id) in TracksDAO
        return album;
    }

    public Collection<Album> getAllAlbums(){
        Collection<Album> albums = new ArrayList<Album>();
        this.jdbcTemplate.query(
                "SELECT * FROM albums", new Object[] { },
                (rs, rowNum) -> new Album(rs.getInt("id"), rs.getString("title"))
        ).forEach(album -> albums.add(album));

        return albums;
    }

    public Album updateAlbum(Album album){


        this.jdbcTemplate.update ( "UPDATE albums SET title = ? WHERE id = ?", album.getTitle(),album.getId());



        return album;
    }

    public boolean deleteAlbum(Album album){
        boolean success = false;

        jdbcTemplate.update("delete from albums where id = ?", album);



        return success;
    }



}
