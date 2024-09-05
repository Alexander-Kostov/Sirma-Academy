export default function MovieList(props) {
    return (
        <>
            <h2>{props.title}</h2>
            <ul>
                <li>{props.movies[0]}</li>
                <li>Second</li>
                <li>Third</li>
            </ul>
        </>
    );
}