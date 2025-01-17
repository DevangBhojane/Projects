document.addEventListener("DOMContentLoaded", function () {
    const firstBtn = document.getElementById("firstBtn");
    const secondBtn = document.getElementById("secondBtn");
    const thirdBtn = document.getElementById("thirdBtn");
    const choiceInput = document.getElementById("choiceInput");
    const contentDiv = document.getElementById("content");
    const error = document.getElementById("error");

    function appendCopyrightNotice(choice) {
        const copyrightNotice = choice === "mario"
            ? "Game trademarks and copyrights are properties of their respective owners. Nintendo properties are trademarks of Nintendo. © 2019 Nintendo."
            : "Star Wars © & TM 2022 Lucasfilm Ltd. All rights reserved. Visual material © 2022 Electronic Arts Inc.";

        const copyrightElement = document.createElement("p");
        copyrightElement.className = "mt-4";
        copyrightElement.textContent = copyrightNotice;
        contentDiv.appendChild(copyrightElement);
    }

    function clearContent() {
        contentDiv.innerHTML = "";
    }

    firstBtn.addEventListener("click", function () {
        clearContent();
        choiceInput.disabled = false;
        fetch("https://csunix.mohawkcollege.ca/~adams/10259/a6_responder.php")
            .then(response => response.text())
            .then(data => {
                contentDiv.innerHTML = `<h1 class="text-center">${data} 000901300</h1>`;
            })
            .catch(error => {
                console.error("Error fetching data:", error);
            });
    });

    secondBtn.addEventListener("click", function () {
        clearContent();
        choiceInput.disabled = false;
        const choice = choiceInput.value.toLowerCase();
        if (choice === "mario" || choice === "starwars") {
            error.innerHTML = "";
            fetch(`https://csunix.mohawkcollege.ca/~adams/10259/a6_responder.php?choice=${choice}`)
                .then(response => response.json())
                .then(data => {
                    const row = document.createElement("div");
                    row.className = "row";

                    let columnClass;
                    if (data.length === 1) {
                        columnClass = "col-md-12";
                    } else if (data.length === 2) {
                        columnClass = "col-md-6";
                    } else {
                        columnClass = "col-md-4";
                    }

                    data.forEach(item => {
                        const div = document.createElement("div");
                        div.className = `${columnClass} mb-4`;
                        div.innerHTML = `
                            <h2>${item.series}</h2>
                            <div class="text-center">
                                <img src="${item.url}" class="img-fluid" alt="${item.name}">
                            </div>
                            <p>${item.name}</p>
                        `;
                        row.appendChild(div);
                    });

                    contentDiv.appendChild(row);

                    appendCopyrightNotice(choice);
                })
                .catch(error => {
                    console.error("Error fetching data:", error);
                });
        } else {
            error.innerHTML = "Invalid choice. Please enter 'mario' or 'starwars'.";
        }
    });

    thirdBtn.addEventListener("click", function () {
        clearContent();
        choiceInput.disabled = false;
        const choice = choiceInput.value.toLowerCase();
        if (choice === "mario" || choice === "starwars") {
            error.innerHTML = "";
            fetch("https://csunix.mohawkcollege.ca/~adams/10259/a6_responder.php", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `choice=${choice}`
            })
                .then(response => response.json())
                .then(data => {
                    if (Array.isArray(data)) {
                        const table = document.createElement("table");
                        table.className = "table mt-4";
                        table.innerHTML = `
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Series</th>
                                    <th>URL</th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        `;

                        const tbody = table.querySelector("tbody");
                        data.forEach(item => {
                            const row = document.createElement("tr");
                            row.innerHTML = `
                                <td>${item.name}</td>
                                <td>${item.series}</td>
                                <td>${item.url}</td>
                            `;
                            tbody.appendChild(row);
                        });

                        contentDiv.appendChild(table);
                    } else {
                        console.error("Invalid response data format. Expected an array.");
                    }

                    appendCopyrightNotice(choice);
                })
                .catch(error => {
                    console.error("Error fetching data:", error);
                });
        } else {
            error.innerHTML = "Invalid choice. Please enter 'mario' or 'starwars'.";
        }
    });
});