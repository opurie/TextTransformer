submitForm = () => {
    let content = document.getElementById("input").value;
    let transformation = document.getElementById("transformation-selector").value;
    if(content === ""){
        alert("Input text is empty!");
    }
    else{
        console.log(`Tranform ${content} using transformation ${transformation}`);
    }
    
    axios.post("transform", {
        text: content,
        transformation: transformation
    }).then((response) => {
        console.log(response);
        document.getElementById("result").value = response.data;
    });
}