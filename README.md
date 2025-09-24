# AIMD-TCP-Congestion-contol
<p>In modern wireless communication networks, managing congestion effectively is crucial to ensure fair bandwidth distribution, minimize delays, and prevent packet loss. As the demand for internet-based services continues to rise—especially on Wi-Fi networks shared among multiple devices—the need for efficient congestion control mechanisms becomes more pressing. Transmission Control Protocol (TCP), which governs a majority of internet data transmission, includes a built-in congestion control strategy that dynamically adjusts the sending rate based on network feedback. One of the most widely adopted strategies is AIMD (Additive Increase Multiplicative Decrease), which combines fast bandwidth probing with responsive back-off when congestion is detected. This project implements a simulation of TCP congestion control behavior in a shared Wi-Fi network, using Java. The simulation involves multiple client devices—each representing a unique user and device type—competing to send data through a common router. Each client adapts its congestion window (cwnd) in real time based on random packet loss probability, mimicking actual wireless link conditions. The simulation models all major TCP behaviors including slow start, congestion avoidance, fast retransmit, and fast recovery. By observing how the congestion window evolves across transmission rounds and comparing throughput received by the router, the project aims to visualize and analyze the dynamic nature of congestion control. Additionally, ASCII-based graphs offer an intuitive way to understand how each client adjusts its sending rate over time. This practical representation of TCP congestion control provides insights into how fairness and efficiency are balanced, and forms a solid foundation for extending the work towards more advanced models like New AIMD, which aim to further optimize responsiveness and network utilization. This simulation is primarily designed for students, researchers, and network engineers working in the fields of computer networks, wireless communication, and transport protocols. It serves as a practical educational tool for understanding the behavior of TCP congestion control mechanisms, especially in Wi-Fi environments where packet loss and competition for bandwidth are common.</p>

<p> Sure! Here are some well-known **organizations** and communities on GitHub that work on networking-related open source projects, ranging from simple tools to complex network analyzers and protocols:

---

### Organizations Working on Networking Open Source

1. **Wireshark Foundation**

   * Maintains the famous **Wireshark** network protocol analyzer.
   * GitHub: [wireshark](https://github.com/wireshark)

2. **Nmap**

   * The team behind the **Nmap** network mapper tool.
   * GitHub: [nmap](https://github.com/nmap)

3. **The TCPDump Group**

   * Maintains **tcpdump** and **libpcap** for packet capture.
   * GitHub: [the-tcpdump-group](https://github.com/the-tcpdump-group)

4. **Cloudflare**

   * Works on many networking and internet security projects, including DNS tools, HTTP/3, and more.
   * GitHub: [cloudflare](https://github.com/cloudflare)

5. **Cilium**

   * Open source networking, security, and observability for cloud-native environments.
   * GitHub: [cilium](https://github.com/cilium)

6. **Tigera**

   * Works on Calico, a networking and network security project for Kubernetes and containers.
   * GitHub: [tigera](https://github.com/tigera)

7. **Facebook (Meta)**

   * Maintains **fbthrift**, **fbnet**, and other networking infrastructure tools.
   * GitHub: [facebook](https://github.com/facebook)

8. **Linux Foundation Networking Projects**

   * Many projects under Linux Foundation like ONAP, OpenDaylight, and FD.io related to networking.
   * Website: [linuxfoundation.org/projects/networking/](https://www.linuxfoundation.org/projects/networking/)

---

### How to Engage with These Organizations

* Browse their repos for beginner-friendly issues.
* Join their community chats or forums for help and discussions.
* Many have contribution guides for new contributors.

---

Want me to help you find beginner issues in any of these organizations? Or do you want recommendations on smaller community-driven projects instead?
</p>
